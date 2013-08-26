package com.kurzandroidu.zakladyandroidu;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class RemoteService extends Service {

    private IRemoteService.Stub mRemoteServiceStub = new IRemoteService.Stub() {

                                                       @Override
                                                       public String[] getViewTypes()
                                                               throws RemoteException {
                                                           return getResources()
                                                                   .getStringArray(
                                                                           R.array.view_types);
                                                       }
                                                   };

    @Override
    public IBinder onBind(Intent intent) {
        return mRemoteServiceStub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
