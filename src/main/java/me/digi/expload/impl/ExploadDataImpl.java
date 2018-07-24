package me.digi.expload.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import me.digi.expload.ExploadData;
import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec;

import java.security.*;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExploadDataImpl implements ExploadData {

    byte[] data;
    byte[] publicKey;
    byte[] sign;

    @Override
    public boolean isSigned() {
        return sign == null || sign.length == 0;
    }

    @Override
    public boolean isSignValid() {
        try {
            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);
            Signature sgr = new EdDSAEngine(MessageDigest.getInstance(spec.getHashAlgorithm()));

            EdDSAPublicKeySpec pk = new EdDSAPublicKeySpec(publicKey, spec);
            PublicKey vKey = new EdDSAPublicKey(pk);

            sgr.initVerify(vKey);
            sgr.update(data);

            return sgr.verify(sign);

        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
            return false;
        }
    }

}
