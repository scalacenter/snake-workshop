---
sidebar_position: 1
---

# Project setup

#### 1. Go to the [scalacenter/snake-workshop](https://github.com/scalacenter/snake-workshop) page on Github:

#### 2. Copy the HTTPS url of the repository:

![Clone repository](/img/snake/github.png)

#### 3. Open a Terminal (on Linux or Mac) or a Command Prompt (on Windows).

#### 4. Clone the snake-workshop repository:

```bash
git clone https://github.com/scalacenter/snake-workshop.git
```

#### 5. Open the snake-workshop directory:

```bash
cd snake-workshop
```

#### 6. Open VS Code:

```bash
code .
```

#### 7. Wait for the Metals pop-up and click `Import build`:

![Import](/img/snake/metals-import.png)

#### 8. Open a terminal in VS Code:

![Terminal](/img/snake/metals-terminal.png)

#### 9. In the terminal in VS Code, start an sbt shell:

```bash
sbt
```

#### 10. In the sbt shell, compile the JavaScript file:

```bash
fastLinkJS
```

#### 11. Copy the path of the `index.html` file :

![index.html](/img/snake/index-html.png)

#### 12. Open it in a web browser.

You should see a big grey square.

#### 13. In the browser open the console:
- `F12` or `Ctrl + Shift + J` in Firefox or Chrome
- `F12` on Internet Explorer or Edge
- `Right-Click`+`Console` on Safari

An error is printed repeateadly.

![web browser](/img/snake/console.png)

#### 14. Click on `Snake.scala` to understand what is missing:

That's where your mission starts.