# Constructr-coordination-demo #

Akka Cluster demo using Constructr-Consul.

## Usage

#### Prerequisites ####

Make sure you have already installed both [Docker Engine and Docker Compose](https://docs.docker.com/compose/install/).

#### Steps ####

- Seed node in Docker: `sbt constructr-coordination-demo/docker:publishLocal`
- Remove docker containers (if exist): `docker rm -f consul1 consul2 akka_node1 akka_node2 akka_node3`
- Run the application: `docker-compose -f constructr-coordination-demo/docker-compose.yml up`

We should see something like this:
```
Starting consul1
Starting consul2
Starting akka_node3
Starting akka_node1
Starting akka_node2
...
consul1       | ==> Starting Consul agent...
consul2       | ==> Starting Consul agent...
...
consul1       |     2016/06/01 15:44:14 [INFO] consul: member 'consul2' joined, marking health alive
consul2       |     2016/06/01 15:44:16 [INFO] agent: Synced node info
...
akka_node3    | 15:44:17 INFO  Constructr [akka.tcp://ConstructR-Consul@172.18.0.4:2552/system/constructr] - Creating constructr-machine, because no seed-nodes defined
akka_node3    | 15:44:17 DEBUG ConstructrMachine [akka.tcp://ConstructR-Consul@172.18.0.4:2552/system/constructr/constructr-machine] - Transitioning to GettingNodes
akka_node2    | 15:44:17 INFO  Constructr [akka.tcp://ConstructR-Consul@172.18.0.5:2552/system/constructr] - Creating constructr-machine, because no seed-nodes defined
akka_node1    | 15:44:17 INFO  Constructr [akka.tcp://ConstructR-Consul@172.18.0.6:2552/system/constructr] - Creating constructr-machine, because no seed-nodes defined
akka_node2    | 15:44:17 DEBUG ConstructrMachine [akka.tcp://ConstructR-Consul@172.18.0.5:2552/system/constructr/constructr-machine] - Transitioning to GettingNodes
akka_node1    | 15:44:17 DEBUG ConstructrMachine [akka.tcp://ConstructR-Consul@172.18.0.6:2552/system/constructr/constructr-machine] - Transitioning to GettingNodes
```
- Consul UI: `http://localhost:8500/ui/`

You can use Consul UI for checking services, nodes, reading/writing key/value data, view/invalidate sessions.

#### HTTP API ####

- Consul:
    - Check nodes: `curl "http://localhost:8500/v1/kv/constructr/ConstructR-Consul/nodes/?recurse&pretty"`

      List node key/value pairs

    - Active sessions: `curl http://localhost:8500/v1/session/list?pretty`

      List active sessions attached to consul nodes

    - Delete session: `curl -XPUT http://localhost:8500/v1/session/destroy/{session-id}`

      If you delete a session, Constructr-Consul won't be allowed to refresh it, so that node will die.

- Akka:
    - Get the member nodes: `curl 127.0.0.1:8000/member-nodes`.

    We can check the akka members in the cluster here (if everything is working properly, we should see this):
     ```
    akka.tcp://ConstructR-Consul@172.18.0.4:2552
    akka.tcp://ConstructR-Consul@172.18.0.5:2552
    akka.tcp://ConstructR-Consul@172.18.0.6:2552
    ```

## Configuration

```
constructr {
  // We need to configure the agent name under which sessions will be saved
  // This property will be typically set by a cluster scheduler service as could be Nomad or Kubernetes.
  consul.agent-name = "consul2"
}
```

In a production environment, you should launch both the consul agent and the akka system together on the same machine
configuring the `consul.agent-name` property according to the host name or ip address, so that if the machine crashes
then the consul session can be properly invalidated.