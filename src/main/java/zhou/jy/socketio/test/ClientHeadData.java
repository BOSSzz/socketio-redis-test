package zhou.jy.socketio.test;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.*;

/**
 * @author zhoujy
 * @date 2019/04/10
 */
public class ClientHeadData implements Serializable {

    private static final long serialVersionUID = 238790253873656662L;

    private UUID sessionId;

    private Map<String, List<String>> headers;
    private InetSocketAddress address;
    private Date time;
    private InetSocketAddress local;
    private String url;
    private Map<String, List<String>> urlParams;
    private boolean xdomain;

    private String transport;

    public ClientHeadData(UUID sessionId, Map<String, List<String>> headers, Map<String, List<String>> urlParams, InetSocketAddress address, Date time, InetSocketAddress local, String url, boolean xdomain, String transport) {
        this.sessionId = sessionId;
        this.address = address;
        this.time = time;
        this.local = local;
        this.url = url;
        this.urlParams = urlParams;
        this.headers = new HashMap<>();
        this.headers = headers;
        this.transport = transport;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public InetSocketAddress getLocal() {
        return local;
    }

    public void setLocal(InetSocketAddress local) {
        this.local = local;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, List<String>> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(Map<String, List<String>> urlParams) {
        this.urlParams = urlParams;
    }

    public boolean isXdomain() {
        return xdomain;
    }

    public void setXdomain(boolean xdomain) {
        this.xdomain = xdomain;
    }
}
