package demo.msa.framework.registry;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class ServiceRegistryImpl implements ServiceRegistry, Watcher {

    private static CountDownLatch latch = new CountDownLatch(1);
    private ZooKeeper zk;

    private static final String REGISTRY_PATH = "/registry";
    private static final int SESSION_TIMEOUT = 5000;



    public ServiceRegistryImpl() {
    }

    public ServiceRegistryImpl(String zkServers) {
        try {
            zk = new ZooKeeper(zkServers, SESSION_TIMEOUT, this);
            latch.await();
            log.debug("connected to zookeeper");
        } catch (IOException e) {
            log.error("connect to zkserver failure", e);
        } catch (InterruptedException e) {
            log.error("connect to zkserver failure", e);
        }

    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        try {
            String registryPath = REGISTRY_PATH;
            if (zk.exists(registryPath, false) == null) {
                zk.create(registryPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                log.debug("create registry node: {}", REGISTRY_PATH);
            }
            String servicePath = REGISTRY_PATH + "/" + serviceName;
            if (zk.exists(servicePath, null) == null) {
                zk.create(servicePath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                log.debug("create service node: {}", servicePath);
            }
            String addressPath = servicePath + "/address-";
            String addressNode = zk.create(addressPath, serviceAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.debug("create address node: {} => {}", addressNode, serviceAddress);

        } catch (InterruptedException e) {
            log.error("create node failure", e);
        } catch (KeeperException e) {
            log.error("create node failure", e);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            latch.countDown();
        }
    }
}
