package social.network.ms_auth.service.security;

import social.network.ms_auth.dto.request.RefreshTokenRequest;
import social.network.ms_auth.service.model.AccessTokenModel;
import social.network.ms_auth.service.model.JwtUserDetails;
import social.network.ms_auth.service.model.RefreshTokenModel;

public interface RefreshTokenService {
    RefreshTokenModel generateRefreshToken(AccessTokenModel model);
    boolean checkRefreshToken(RefreshTokenModel model);
    RefreshTokenModel deleteRefreshToken(RefreshTokenRequest request);
    JwtUserDetails getUserDetails(RefreshTokenModel model);
}