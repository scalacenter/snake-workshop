---
sidebar_position: 1
---

# Setup Scala

:::caution
This page is reserved for **Linux** users. Instructions for other operating system can be found in:
- [**Install - Mac**](../mac/scala-setup)
- [**Install - Windows**](../windows/scala-setup)
:::

## Install Scala using Coursier

Coursier is the Scala artifact fetcher, we will use it to download the indispensable Scala toolbox.

1. Open a terminal.

![Open terminal](/img/installation/linux/terminal.png)

:::tip
To execute an action in the terminal you must type a command then hit the `Enter` button.
For example, you can type the `pwd` command to print the working directory:

Try it yourself: copy paste the command below into the terminal and hit `Enter`.
```bash
pwd
```
:::

2. In the terminal, download the `cs` file:

```bash
curl -fLo cs https://git.io/coursier-cli-"$(uname | tr LD ld)"
```

3. Make the downloaded file executable with:

```bash
chmod +x cs
```

:::info
The `chmod` command does not print anything back to you. That's okay.

Other commands below won't print anything either.
:::

4. Execute the `cs` file to setup scala:

```bash
./cs setup --yes
```

5. Reload your `.profile` file:

```bash
. ~/.profile
```

6. Finally, you can remove the coursier file:

```bash
rm cs
```

## Check the installation of Scala

1. Check that Coursier is installed

```bash
cs --version
```

It should print:

```
2.0.16
```

2. Check that Java is installed

```bash
java -version
```

It should print something like:
```bash
openjdk version "1.8.0_275"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_275-b01)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.275-b01, mixed mode)
```

3. Check that scala is installed

```bash
scala -version
```

It should print:
```
Scala code runner version 2.13.6 -- Copyright 2002-2021, LAMP/EPFL and Lightbend, Inc.
```
