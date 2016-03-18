# ConstructR-Consul #

This library enables to use Consul as cluster coordinator in a ConstructR based node.  

[ConstructR](https://github.com/hseeberger/constructr) aims at cluster bootstrapping (construction) by using a coordination service and provides etcd as the default one. By means of this library, you will be able to use [Consul](https://www.consul.io/) as coordination service instead.

You will need to add the following dependency in your `build.sbt` in addition to the core ConstructR ones:

```
// All releases including intermediate ones are published here,
// final ones are also published to Maven Central.
resolvers += Resolver.bintrayRepo("tecsisa", "maven-bintray-repo")

libraryDependencies ++= Vector(
  "com.tecsisa" %% "constructr-coordination-consul" % "0.1.0",
  ...
)
```

## Configuration ##

Check [this section](https://github.com/hseeberger/constructr#coordination) in ConstructR for general information about configuration.

## Testing

Requirements:
  - consul needs to be running, e.g. via ` docker run -d -p 8501:8500 --name constructr-consul gliderlabs/consul-server:0.6 -server -bootstrap`

## Contribution policy ##

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

Please make sure to follow these conventions:
- For each contribution there must be a ticket (GitHub issue) with a short descriptive name, e.g. "Respect seed-nodes configuration setting"
- Work should happen in a branch named "ISSUE-DESCRIPTION", e.g. "32-respect-seed-nodes"
- Before a PR can be merged, all commits must be squashed into one with its message made up from the ticket name and the ticket id, e.g. "Respect seed-nodes configuration setting (closes #32)"

## License ##

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
