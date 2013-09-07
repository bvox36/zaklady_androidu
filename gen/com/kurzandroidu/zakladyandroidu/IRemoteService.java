/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\---Osobni---\\---Živnost---\\Spoluprace\\Prague IT Education\\zaklady_androidu\\src\\com\\kurzandroidu\\zakladyandroidu\\IRemoteService.aidl
 */
package com.kurzandroidu.zakladyandroidu;
public interface IRemoteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.kurzandroidu.zakladyandroidu.IRemoteService
{
private static final java.lang.String DESCRIPTOR = "com.kurzandroidu.zakladyandroidu.IRemoteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.kurzandroidu.zakladyandroidu.IRemoteService interface,
 * generating a proxy if needed.
 */
public static com.kurzandroidu.zakladyandroidu.IRemoteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.kurzandroidu.zakladyandroidu.IRemoteService))) {
return ((com.kurzandroidu.zakladyandroidu.IRemoteService)iin);
}
return new com.kurzandroidu.zakladyandroidu.IRemoteService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getViewTypes:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.getViewTypes();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.kurzandroidu.zakladyandroidu.IRemoteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String[] getViewTypes() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getViewTypes, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getViewTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public java.lang.String[] getViewTypes() throws android.os.RemoteException;
}
