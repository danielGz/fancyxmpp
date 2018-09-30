package co.fitcom.xmpp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import co.fitcom.fancyxmpp.ConnectionOptions;
import co.fitcom.fancyxmpp.Manager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Manager manager = Manager.getInstance();
        ConnectionOptions options = new ConnectionOptions();
        options.setUsername("username");
        options.setPassword("password");
        options.setDomain("domain");
        AbstractXMPPConnection connection = manager.createConnection(options);
        manager.connect((XMPPTCPConnection) connection,true);
    }
}
