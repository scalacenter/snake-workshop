---
sidebar_position: 4
---

# Node.js

:::caution
This page is reserved for **Linux** users. Instructions for other operating system can be found in:
- [**Install - Mac**](../mac/nodejs)
- [**Install - Windows**](../windows/nodejs)
:::

#### 1. Open the terminal

![Open terminal](/img/installation/linux/terminal.png)

#### 2. Add Nodesource to your `apt` sources
```bash
curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash -
```

#### 3. Install Node.js

```bash
sudo apt-get install -y nodejs
```

#### 4. Check that Node.js is installed
```bash
node --version
```

It should print:
```
v16.12.0
```

#### 5. Check that npm is installed
```bash
npm --version
```

It should print:
```
8.1.0
```

