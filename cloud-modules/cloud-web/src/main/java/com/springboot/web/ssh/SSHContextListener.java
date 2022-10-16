package com.springboot.web.ssh;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听ssh上下文
 */
@WebListener
@Component
public class SSHContextListener implements ServletContextListener {
    private static final Log log = LogFactory.getLog(SSHContextListener.class);
    private SSHConnection ssh;

    public SSHContextListener() {
        super();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // servlet初始化时 建立连接
        try {
            ssh = new SSHConnection();
            ssh.connectionSSH();
            log.info("成功建立SSH连接");
        } catch (Throwable e) {
            log.info("SSH连接失败！");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // servlet销毁时 断开连接
        log.info("Context destroyed ... !\n\n\n");
        try {
            ssh.closeSSH(); // disconnect
            log.info("\n\n\n成功断开SSH连接!\n\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("\n\n\n断开SSH连接出错！\n\n\n");
        }
    }
}

