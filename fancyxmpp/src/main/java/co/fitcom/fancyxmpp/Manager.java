package co.fitcom.fancyxmpp;


import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by triniwiz on 5/26/18
 */
public class Manager {
    private Handler mHandler;
    private HandlerThread mThread;
    private static Manager mManager;

    private Manager() {
        mThread = new HandlerThread("XMPP Thread", android.os.Process.THREAD_PRIORITY_BACKGROUND);
        mThread.start();
        mHandler = new Handler(mThread.getLooper());
    }

    public static Manager getInstance() {
        if (mManager == null) {
            mManager = new Manager();
        }
        return mManager;
    }

    public AbstractXMPPConnection createConnection(ConnectionOptions options) {

        XMPPTCPConnection connection = null;
        try {
            XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword(options.getUsername(), options.getPassword());


            if(options.getResource() != null){
                builder.setResource(options.getResource());
            }

            if (options.getDomain() != null) {
                builder.setXmppDomain(options.getDomain());
            }

            if (options.getHost() != null) {
                builder.setHost(options.getHost());
            }

            builder.setCompressionEnabled(options.getCompression());

            builder.setPort(options.getPort());

            builder.setKeystoreType(null);

            if (options.getSasl() != null) {
                builder.addEnabledSaslMechanism(options.getSasl());
            }

            connection = new XMPPTCPConnection(builder.build());
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void connect(final XMPPTCPConnection connection) {
        connect(connection, false);
    }

    public void connect(final XMPPTCPConnection connection, final boolean autoLogin) {
        if (connection != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (autoLogin) {
                            connection.connect().login();
                        } else {
                            connection.connect();
                        }

                    } catch (SmackException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XMPPException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void disconnect(XMPPTCPConnection connection) {
        if (connection != null) {
            connection.disconnect();
        }
    }

    public void login(final XMPPTCPConnection connection) {
        if (connection != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.login();
                    } catch (SmackException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XMPPException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}