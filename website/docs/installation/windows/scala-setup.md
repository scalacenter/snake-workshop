---
sidebar_position: 1
---

# Setup Scala

:::caution
This page is reserved for **Windows** users. Instructions for other operating system can be found in:
- [**Install - Linux**](../linux/scala-setup)
- [**Install - Mac**](../mac/scala-setup)
:::

## Install Scala using Coursier

Coursier is the Scala artifact fetcher, we will use it to install the standard Scala toolbox.

#### 1. Go to [Microsoft website](https://www.microsoft.com/en-us/download/details.aspx?id=48145) and click the `Download` button.

#### 2. Select `vc_redist.x64.exe` and click `Next` to download Visual C++ redistribuable.

![Download vc_redist.x64.exe](/img/installation/windows/redist.png)

#### 3. Execute the downloaded `VC_redist.x64.exe` and follow the installation steps:
- Agree the license terms and conditions
- Click Install

#### 4. Open the Command Prompt.

![Open Command Prompt](/img/installation/windows/cmd.png)

#### 5. In the Command Prompt, download the `cs.exe` file:

```batch
bitsadmin /transfer cs-cli https://git.io/coursier-cli-windows-exe "%cd%\cs.exe"
```

#### 6. Execute the `cs.exe` file to setup scala:

```batch
.\cs setup --yes
```

#### 7. Finally, you can remove the coursier file:

```batch
del cs.exe
```

## Check the installation of Scala

#### 1. Close and reopen the Command Prompt to refresh the environment variables.

#### 2. Check that Coursier is installed:

```batch
cs --version
```

It should print:

```
2.0.13
```

#### 3. Check that Java is installed:

```batch
java -version
```

It should print something like:
```
openjdk version "1.8.0_292"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_292-b10)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.292-b10, mixed mode)
```

#### 4. Check that Scala is installed:

```batch
scala -version
```

It should print:
```
Scala code runner version 2.13.6 -- Copyright 2002-2021, LAMP/EPFL and Lightbend, Inc.
```
