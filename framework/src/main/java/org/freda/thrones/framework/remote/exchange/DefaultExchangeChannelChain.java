package org.freda.thrones.framework.remote.exchange;

import lombok.extern.slf4j.Slf4j;
import org.freda.thrones.framework.common.URL;
import org.freda.thrones.framework.exceptions.LinkingException;
import org.freda.thrones.framework.msg.ProcedureReqMsg;
import org.freda.thrones.framework.msg.ProcedureRespMsg;
import org.freda.thrones.framework.remote.ChannelChain;
import org.freda.thrones.framework.remote.future.CommonFuture;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * Create on 2018/8/18 14:18
 */
@Slf4j
public class DefaultExchangeChannelChain implements ExchangeChannelChain {

    private final ChannelChain channelChain;

    private volatile boolean closed = false;

    public DefaultExchangeChannelChain(ChannelChain channelChain) {
        if (Objects.isNull(channelChain)) {
            throw new IllegalArgumentException("channelChain is null");
        }
        this.channelChain = channelChain;
    }

    @Override
    public CommonFuture request(Object request) throws LinkingException {
        return null;
    }

    @Override
    public CommonFuture request(Object request, int timeout) throws LinkingException {
        return null;
    }

    @Override
    public ExchangeHandler getExchangeHandler() {
        return null;
    }

    @Override
    public void close() {
        try {
            channelChain.close();
        } catch (Throwable e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public void close(int timeout) {
        if (closed) {
            return;
        }
    }

    @Override
    public void closing() {
        channelChain.closing();
    }

    @Override
    public boolean closed() {
        return closed;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return channelChain.getLocalAddress();
    }

    @Override
    public boolean isConnected() {
        return channelChain.isConnected();
    }

    @Override
    public boolean hasAttribute(String key) {
        return channelChain.hasAttribute(key);
    }

    @Override
    public Object getAttribute(String key) {
        return channelChain.getAttribute(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        channelChain.setAttribute(key, value);
    }

    @Override
    public void removeAttribute(String key) {
        channelChain.removeAttribute(key);
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return channelChain.getLocalAddress();
    }

    @Override
    public URL getUrl() {
        return channelChain.getUrl();
    }

    @Override
    public void send(Object message) throws LinkingException {
        channelChain.send(message, Boolean.FALSE);
    }

    @Override
    public void send(Object message, boolean sent) throws LinkingException {
        if (message instanceof ProcedureReqMsg || message instanceof ProcedureRespMsg) {
            channelChain.send(message, sent);
        } else {
            throw new IllegalArgumentException("Unknown parameter type");
        }
    }

    @Override
    public void reset(URL url) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultExchangeChannelChain that = (DefaultExchangeChannelChain) o;

        return channelChain.equals(that.channelChain);
    }

    @Override
    public int hashCode() {
        return channelChain.hashCode();
    }

    @Override
    public String toString() {
        return channelChain.toString();
    }
}
