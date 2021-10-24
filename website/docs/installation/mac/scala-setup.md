---
sidebar_position: 1
---

# Setup Scala  - TODO

:::caution
This page is reserved for **Mac** users. Instructions for other operating system can be found in:
- [**Install - Linux**](../linux/scala-setup)
- [**Install - Windows**](../windows/scala-setup)
:::

## Install Scala using Coursier

Coursier is the Scala artifact fetcher, we will use it to download the indispensable Scala toolbox.

1. Open a terminal.
2. In the terminal, download couriser by copy-pasting and executing:

```bash
curl -fLo cs https://git.io/coursier-cli-"$(uname | tr LD ld)"
```

3. Make the downloaded file executable with:

```bash
chmod +x cs
```

4. Execute the coursier file to setup scala:

```bash
./cs setup
```

5. If prompted to install Java, hit `Enter` to validate
6. If prompted to uptade your `.profile` file, hit `Enter` to validate 
7. Reload your `.profile` file:

```bash
. ~/.profile
```

8. Finally, you can remove the coursier file:

```bash
rm cs
```

## Check the installation of Scala

1. Check that Coursier is installed

```bash
cs --version
```

2. Check that Java is installed

```bash
java -version
```

3. Check that scala is installed

```bash
scala -version
```
