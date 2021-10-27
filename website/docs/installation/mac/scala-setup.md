---
sidebar_position: 1
---

# Setup Scala

:::caution
This page is reserved for **Mac** users. Instructions for other operating system can be found in:
- [**Install - Linux**](../linux/scala-setup)
- [**Install - Windows**](../windows/scala-setup)
:::

## Install Scala using Coursier

Coursier is the Scala artifact fetcher, we will use it to install the standard Scala toolbox.

#### 1. Open a terminal.

![Open terminal](/img/installation/mac/terminal.png)

#### 2. In the terminal, install `cs` using Homebrew:

```bash
brew install coursier/formulas/coursier
```

:::info
If you have never used Homebrew before, you can install it with:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```
:::

#### 3. Use Coursier to setup scala:

```bash
cs setup --yes
```

#### 5. Reload your `.profile` file:

```bash
. ~/.profile
```

## Check the installation of Scala

#### 1. Check that Java is installed:

```bash
java -version
```

It should print something like:
```
openjdk version "1.8.0_275"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_275-b01)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.275-b01, mixed mode)
```

#### 2. Check that Scala is installed:

```bash
scala -version
```

It should print:
```
Scala code runner version 2.13.6 -- Copyright 2002-2021, LAMP/EPFL and Lightbend, Inc.
```
