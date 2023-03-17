package token;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

public class TokenKey {
    
    private Key key;

    public TokenKey(){
        String secretKey = "JXTjZbu2Lqi8v0ecwAwXuN0v3cuUvqtut1WKrh6wOGXRYFSjQ3E4i9ECK4JPJgj0k" + 
        "NXBT462mK5aez5B2TwyYMvGOyDnDPPLVidKitIKgxTxnA0VFXaDTkevbdxRY5M2FlzFbuUIbF7sIBGrEJyMkQ42Hr" +
        "uDZRVxQBoOeevNvVoFxPBVmzkjvBuIKPnTd59ojKrms0EN5rmSZdbp8Rs2FmlHX6BzK1q6RLkZRxYCPcwTZQfNy7MnMMdG6YPGO3gC";
        byte[] keyBytes = secretKey.getBytes();
        key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public Key getKey(){
        return key;
    }
}
