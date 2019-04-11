package zhou.jy.socketio.test;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhoujy
 * @date 2019/04/09
 */
public class SocketIOMain {

    private final static Logger logger = LoggerFactory.getLogger(SocketIOMain.class);

    public static void main(String[] args) throws InterruptedException {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(Integer.parseInt(args[0]));
        SocketIOServer server = new SocketIOServer(config);
        server.setPipelineFactory(new MyPipeLineFactory());
        server.addConnectListener(new ConnectListener() {// 添加客户端连接监听器
            @Override
            public void onConnect(SocketIOClient client) {
                logger.info(client.getRemoteAddress() + " web客户端接入");
                client.sendEvent("helloPush", "hello");
            }
        });
        // 握手请求
        server.addEventListener("helloevent", HelloUid.class, new DataListener<HelloUid>() {
            @Override
            public void onData(final SocketIOClient client, HelloUid data, AckRequest ackRequest) {
                // 握手
                String userid = data.getUid();
                logger.info(Thread.currentThread().getName() + "web读取到的userid：" + userid);


                // send message back to client with ack callback
                // WITH data
                client.sendEvent("hellopush", new AckCallback<String>(String.class) {
                    @Override
                    public void onSuccess(String result) {
                        logger.info("ack from client: " + client.getSessionId() + " data: " + result);
                    }
                }, data);


            }
        });

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }
}
