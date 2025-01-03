package com.ruoyi.system.service;

import cn.hutool.core.codec.Base58;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;


@Service
public class Web3Service {


    public Boolean validateSolana(String signature, String content, String walletAddress) throws SignatureException {
        try {
            byte[] publicKeyBytes = Base58.decode(walletAddress);
            byte[] signatureBytes = Hex.decode(signature);

            Ed25519PublicKeyParameters publicKey = new Ed25519PublicKeyParameters(publicKeyBytes, 0);
            Ed25519Signer signer = new Ed25519Signer();
            signer.init(false, publicKey);
            byte[] message = content.getBytes();
            signer.update(message, 0, message.length);
            return signer.verifySignature(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     *
     * @param signature
     * @param content
     * @param walletAddress
     * @return
     */
    public Boolean validateETH(String signature, String content, String walletAddress) throws SignatureException {
        if (content == null) {
            return false;
        }
        // todo ,Hash.sha3 content.getBytes()
        //
        // byte[] msgHash = Hash.sha3(content.getBytes());
        byte[] msgHash = content.getBytes();
        //
        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        byte v = signatureBytes[signatureBytes.length - 1];
        if (v < 27) {
            v += 27;
        }

        //,
        Sign.SignatureData signatureData = new Sign.SignatureData(
            v,
            Arrays.copyOfRange(signatureBytes, 0, 32),
            Arrays.copyOfRange(signatureBytes, 32, 64));
        //
        BigInteger publicKey = Sign.signedPrefixedMessageToKey(msgHash, signatureData);
        // ()
        String parseAddress = "0x" + Keys.getAddress(publicKey);
        //
        return parseAddress.equalsIgnoreCase(walletAddress);
    }

}
