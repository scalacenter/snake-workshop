---
sidebar_position: 2
---

# VS Code  - TODO

In this section we will install VS Code and two extensions:
- Metals: the Scala language extension
- Live Share: an extension that enables collaborative session on the same code.

## Install VS Code

1. Go to https://code.visualstudio.com/Download
2. Download the `.deb` package.
3. Open the terminal
4. Install the downloaded package

```bash
sudo apt install <.deb file>
```

where `<.deb file>` is the path of the downloaded package.
Typically it would be:

```bash
sudo apt install ~/Downloads/code_1.61.2-1634656828_amd64.deb
```

4. Check that Visual Studio Code is succefully installed

```bash
code -v
```

It should print something like,
```
1.61.2
6cba118ac49a1b88332f312a8f67186f7f3c1643
x64
```

## Install Metals

1. Start VS Code
2. Click the extensions icon in the left bar
3. Search `metals`
4. Click the `Scala (Metals)` extension and click the `install` button
5. Wait for the pop-up and click reload
6. You should see a new Metals icon in the left bar

## Install Live Sharing

1. In VS Code
2. Click the extensions icon in the left bar
3. Search `live share`
4. Click the `Live Share` extension and click the `install` button
5. Wait for the pop-up and click reload
6. You should see the Live Share icon in the left bar
