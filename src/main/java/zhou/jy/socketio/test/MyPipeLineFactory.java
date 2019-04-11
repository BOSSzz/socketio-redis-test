package zhou.jy.socketio.test;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOChannelInitializer;
import com.corundumstudio.socketio.ack.AckManager;
import com.corundumstudio.socketio.handler.ClientHead;
import com.corundumstudio.socketio.namespace.NamespacesHub;
import com.corundumstudio.socketio.scheduler.CancelableScheduler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

import java.lang.reflect.Field;

/**
 * @author zhoujy
 * @date 2019/04/10
 */
@ChannelHandler.Sharable
public class MyPipeLineFactory extends SocketIOChannelInitializer {

    public MyPipeLineFactory() {
        super();
        try {
            Field field = SocketIOChannelInitializer.class.getDeclaredField("clientsBox");
            field.setAccessible(true);
            field.set(this, new ClientsRedisBox());
        } catch (Exception e) {

        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        super.handlerAdded(ctx);
    }

    @Override
    public void start(Configuration configuration, NamespacesHub namespacesHub) {
        super.start(configuration, namespacesHub);
        try {
            AckManager ackManager;
            CancelableScheduler disconnectScheduler;
            Field field = SocketIOChannelInitializer.class.getDeclaredField("ackManager");
            field.setAccessible(true);
            ackManager = (AckManager) field.get(this);
            field = SocketIOChannelInitializer.class.getDeclaredField("scheduler");
            field.setAccessible(true);
            disconnectScheduler = (CancelableScheduler) field.get(this);
            field = SocketIOChannelInitializer.class.getDeclaredField("clientsBox");
            field.setAccessible(true);
            ClientsRedisBox clientsRedisBox = (ClientsRedisBox) field.get(this);
            clientsRedisBox.setAckManager(ackManager);
            clientsRedisBox.setConfiguration(configuration);
            clientsRedisBox.setDisconnectable(this);
            clientsRedisBox.setDisconnectScheduler(disconnectScheduler);
            clientsRedisBox.setStoreFactory(configuration.getStoreFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        super.initChannel(ch);
    }

    @Override
    protected void addSslHandler(ChannelPipeline pipeline) {
        super.addSslHandler(pipeline);
    }

    @Override
    protected void addSocketioHandlers(ChannelPipeline pipeline) {
        super.addSocketioHandlers(pipeline);
    }

    @Override
    public void onDisconnect(ClientHead client) {
        super.onDisconnect(client);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
