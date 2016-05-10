package com.viglle.carmanual.utils.net;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/27.
 */
public interface HttpHandlerInterface {
    void onSuccess(String data);
    void onFailure(int statusCode,IOException e);

}
