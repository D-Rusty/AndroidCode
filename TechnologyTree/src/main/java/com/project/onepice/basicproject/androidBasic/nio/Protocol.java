package com.project.onepice.basicproject.androidBasic.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * Created by onepice2015 on 16/9/26.
 */

public interface Protocol {
    public void handleAccept(SelectionKey key) throws IOException;

    public void handleRead(SelectionKey key) throws IOException;

    public void handleWrite(SelectionKey key) throws IOException;
}
