---
sidebar_position: 1
---

# Introduction

## Scala

Scala is a high-level programming language that is safe and concise.

You can use it to create programs of all size and complexity: a game, a web application, a streaming service...

One of the simplest program you can write with Scala is the hello world program:

```scala title="hello-world.scala"
@main def helloWorld: Unit =
  println("Hello, World!")
```

Scala is a cross-platform language:
 - you can run a Scala program on any personal computer or server using the Java Virtual Machine (JVM)
 - you can run it on a web-browser using the Javascript technology

This makes Scala the perfect general purpose programming language.

Writing programs by hand is not an easy task, but Scala has a rich ecosystem of tools to empower you write Scala programs.

Let's discover some of these tools before installing them.

## Coursier

Coursier is the Scala artifact fetcher.
It can download Scala programs from the web and make them available locally as libraries or applications.

In particular Coursier can install all the programming tools you need as a Scala programmer.
It has a special command `setup` that installs:
- scalac: the Scala compiler
- scala: the Scala REPL and runner
- sbt: the most used Scala build tool
- and others: ammonite, sbtn...

scalac and scala are the primitive tools of the Scala toolbox.
We won't use them directly.
Instead we will use the sbt build tool which uses scalac and scala underneath.

## sbt

sbt is the oldest and most used build tool of the Scala ecosystem.

It helps you configure and organize your Scala project, so that you can compile it, test it, run it, package it and deploy it.

The main configuration of an sbt project is written in a `build.sbt` file at the root of a project. 

```scala title="build.sbt"
scalaVersion := "3.1.0" // Using the version 3.1.0 of the Scala language

// Using the scalajs-dom library to interact with a web page
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.2.0" 
```

The main Scala files of an sbt project are written in the `src/main/scala` folder and the test files are written in the `src/main/test` folder.

We will use [Coursier](#coursier) to install sbt.

## VS Code

Visual Studio Code (VS Code) is a lightweight code editor created by Microsoft.
It supports an incredible number of languages through its extension system.

Its more popular extension for Scala is called Metals.
We will use VS Code and Metals to write and navigate Scala code.

We will also use the Live Share extension to allow several people to work on the same code files remotely.

## Git and Github

Git is the most popular distributed version control system.
It helps developers work on the same project asynchronously and to manage different versions of that project.

In the Git terminology, a project is called a repository.
You can create a repository locally then push it online to make your code available for other programmers.
You can also clone someone else's repository, to have it locally, test it, modify it and make it your own project.

Github (https://github.com/) is a free and popular hosting service for git repositories.
That's where you should go to find some cool projects, run them locally and even contribute to them.