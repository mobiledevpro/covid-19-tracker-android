package com.mobiledevpro.remote.interceptor;

import android.content.Context;

import com.mobiledevpro.remote.R;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Interceptor to handle connection errors and return a readable message to user
 * <p>
 * Created by Dmitriy Chernysh on 12/3/19.
 * <p>
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
public class HandleHttpErrorsInterceptor implements Interceptor {

    private Context mAppContext;

    public HandleHttpErrorsInterceptor(Context appContext) {
        mAppContext = appContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            return chain.proceed(chain.request());
        } catch (ConnectException | SocketTimeoutException | UnknownHostException e) {
            throw new IOException(
                    mAppContext.getResources().getString(R.string.message_check_internet_connection)
            );
        }
    }
}
