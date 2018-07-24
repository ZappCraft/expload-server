package me.digi.expload;

public interface ExploadData {

    byte[] getData();

    byte[] getPublicKey();

    byte[] getSign();

    boolean isSigned();

    boolean isSignValid();

}
