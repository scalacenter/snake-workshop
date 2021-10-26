---
sidebar_position: 3
---

# VS Code

:::caution
This page is reserved for **Linux** users. Instructions for other operating system can be found in:
- [**Install - Mac**](../mac/vscode)
- [**Install - Windows**](../windows/vscode)
:::

In this page we will install VS Code and two extensions:
- Metals: the Scala language extension
- Live Share: an extension that enables collaborative session on the same code.

## Install VS Code

#### 1. Go to Visual Studio [download page](https://code.visualstudio.com/Download)
#### 2. Download the `.deb` package.

![Download VS Code](/img/installation/linux/download-vscode.png)

#### 3. Open the terminal

![Open terminal](/img/installation/linux/terminal.png)

#### 4. Install the downloaded package

```bash
sudo apt install <.deb file>
```

where `<.deb file>` is the path of the downloaded package.
Typically it would be:

```bash
sudo apt install ~/Downloads/code_1.61.2-1634656828_amd64.deb
```

#### 5. Check that Visual Studio Code is succefully installed

```bash
code -v
```

It should print something like:
```
1.61.2
6cba118ac49a1b88332f312a8f67186f7f3c1643
x64
```

## Install Metals

#### 1. Open VS Code

![Open VS Code](/img/installation/linux/vscode.png)

#### 2. Click the extensions icon in the left bar

![Open Extensions](/img/installation/linux/vscode-extensions.png)

#### 3. Search `metals`
#### 4. Click the `Scala (Metals)` extension and click the `Install` button

![Install Metals](/img/installation/linux/vscode-metals.png)

#### 5. Wait until you see a new Metals icon in the left bar

## Install Live Sharing

#### 1. In VS Code, click the extensions icon in the left bar
#### 2. Search `live share`
#### 3. Click the `Live Share` extension and click the `Install` button

![Install Live Share](/img/installation/linux/vscode-live-share.png)

#### 4. Wait until you see the Live Share icon in the left bar
