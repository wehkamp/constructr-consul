# ConstructR-Consul #

[![Build Status](https://travis-ci.org/Tecsisa/constructr-consul.svg?branch=master)](https://travis-ci.org/Tecsisa/constructr-consul)
[![Join the chat at https://gitter.im/Tecsisa/constructr-consul](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/Tecsisa/constructr-consul?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This library enables to use Consul as cluster coordinator in a ConstructR based node.  

[ConstructR](https://github.com/hseeberger/constructr) aims at cluster bootstrapping (construction) by using a coordination service and provides etcd as the default one. By means of this library, you will be able to use [Consul](https://www.consul.io/) as coordination service instead.

You will need to add the following dependency in your `build.sbt` in addition to the core ConstructR ones:

```scala
// All releases including intermediate ones are published here,
// final ones are also published to Maven Central.
resolvers += Resolver.bintrayRepo("tecsisa", "maven-bintray-repo")

libraryDependencies ++= Vector(
  "com.tecsisa" %% "constructr-coordination-consul" % "0.8.0",
  ...
)
```

## Configuration ##

Check [this section](https://github.com/hseeberger/constructr#coordination) in ConstructR for general information about configuration.

Configure the Consul agent name under the agent-name configuration setting:

```
constructr.consul.agent-name = name
```

We strongly recommend configuring this property so that Consul can release every session created by the ConstructR nodes and later reconnections can work properly. More details [here](https://www.consul.io/docs/internals/sessions.html).
Not configuring this property might lead to inconsistent state. This configuration is typically carried out by a cluster scheduler that will have the consul agent name available. Of course, manual configuration is always possible too.

If you are using the [ACL System](https://www.consul.io/docs/guides/acl.html) configure the Consul token:
```
constructr.consul.access-token = token
```

## Testing

Requirements:
  - consul needs to be running, e.g. via ` docker run -d -p 8501:8500 --name constructr-consul consul:1.0.2 agent -server -bootstrap -client=0.0.0.0`

## Demo

We have built a working demo for you. [Try it out!](constructr-coordination-demo/)

## Contribution policy ##

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

Please make sure to follow these conventions:
- For each contribution there must be a ticket (GitHub issue) with a short descriptive name, e.g. "Respect seed-nodes configuration setting"
- Work should happen in a branch named "ISSUE-DESCRIPTION", e.g. "32-respect-seed-nodes"
- Before a PR can be merged, all commits must be squashed into one with its message made up from the ticket name and the ticket id, e.g. "Respect seed-nodes configuration setting (closes #32)"

## License ##

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
