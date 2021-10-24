"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[458],{3905:function(e,t,a){a.d(t,{Zo:function(){return u},kt:function(){return m}});var n=a(7294);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function o(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function l(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?o(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):o(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function i(e,t){if(null==e)return{};var a,n,r=function(e,t){if(null==e)return{};var a,n,r={},o=Object.keys(e);for(n=0;n<o.length;n++)a=o[n],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(n=0;n<o.length;n++)a=o[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var s=n.createContext({}),c=function(e){var t=n.useContext(s),a=t;return e&&(a="function"==typeof e?e(t):l(l({},t),e)),a},u=function(e){var t=c(e.components);return n.createElement(s.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},d=n.forwardRef((function(e,t){var a=e.components,r=e.mdxType,o=e.originalType,s=e.parentName,u=i(e,["components","mdxType","originalType","parentName"]),d=c(a),m=r,h=d["".concat(s,".").concat(m)]||d[m]||p[m]||o;return a?n.createElement(h,l(l({ref:t},u),{},{components:a})):n.createElement(h,l({ref:t},u))}));function m(e,t){var a=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var o=a.length,l=new Array(o);l[0]=d;var i={};for(var s in t)hasOwnProperty.call(t,s)&&(i[s]=t[s]);i.originalType=e,i.mdxType="string"==typeof e?e:r,l[1]=i;for(var c=2;c<o;c++)l[c]=a[c];return n.createElement.apply(null,l)}return n.createElement.apply(null,a)}d.displayName="MDXCreateElement"},3795:function(e,t,a){a.r(t),a.d(t,{frontMatter:function(){return i},contentTitle:function(){return s},metadata:function(){return c},toc:function(){return u},default:function(){return d}});var n=a(7462),r=a(3366),o=(a(7294),a(3905)),l=["components"],i={sidebar_position:1},s="Introduction",c={unversionedId:"installation/intro",id:"installation/intro",isDocsHomePage:!1,title:"Introduction",description:"Scala",source:"@site/docs/installation/intro.md",sourceDirName:"installation",slug:"/installation/intro",permalink:"/snake-workshop/docs/installation/intro",editUrl:"https://github.com/scalacenter/snake-workshop/edit/website/website/docs/installation/intro.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"tutorialSidebar",next:{title:"Setup Scala",permalink:"/snake-workshop/docs/installation/linux/scala-setup"}},u=[{value:"Scala",id:"scala",children:[],level:2},{value:"Coursier",id:"coursier",children:[],level:2},{value:"sbt",id:"sbt",children:[],level:2},{value:"VS Code",id:"vs-code",children:[],level:2},{value:"Git and Github",id:"git-and-github",children:[],level:2}],p={toc:u};function d(e){var t=e.components,a=(0,r.Z)(e,l);return(0,o.kt)("wrapper",(0,n.Z)({},p,a,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("h1",{id:"introduction"},"Introduction"),(0,o.kt)("h2",{id:"scala"},"Scala"),(0,o.kt)("p",null,"Scala is a high-level programming language that is safe and concise."),(0,o.kt)("p",null,"You can use it to create programs of all size and complexity: a game, a web application, a streaming service..."),(0,o.kt)("p",null,"One of the simplest program you can write with Scala is the hello world program:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-scala",metastring:'title="hello-world.scala"',title:'"hello-world.scala"'},'@main def helloWorld: Unit =\n  println("Hello, World!")\n')),(0,o.kt)("p",null,"Scala is a cross-platform language:"),(0,o.kt)("ul",null,(0,o.kt)("li",{parentName:"ul"},"you can run a Scala program on any personal computer or server using the Java Virtual Machine (JVM)"),(0,o.kt)("li",{parentName:"ul"},"you can run it on a web-browser using the Javascript technology")),(0,o.kt)("p",null,"This makes Scala the perfect general purpose programming language."),(0,o.kt)("p",null,"Writing programs by hand is not an easy task, but Scala has a rich ecosystem of tools to empower you write Scala programs."),(0,o.kt)("p",null,"Let's discover some of these tools before installing them."),(0,o.kt)("h2",{id:"coursier"},"Coursier"),(0,o.kt)("p",null,"Coursier is the Scala artifact fetcher.\nIt can download Scala programs from the web and make them available locally as libraries or applications."),(0,o.kt)("p",null,"In particular Coursier can install all the programming tools you need as a Scala programmer.\nIt has a special command ",(0,o.kt)("inlineCode",{parentName:"p"},"setup")," that installs:"),(0,o.kt)("ul",null,(0,o.kt)("li",{parentName:"ul"},"scalac: the Scala compiler"),(0,o.kt)("li",{parentName:"ul"},"scala: the Scala REPL and runner"),(0,o.kt)("li",{parentName:"ul"},"sbt: the most used Scala build tool"),(0,o.kt)("li",{parentName:"ul"},"and others: ammonite, sbtn...")),(0,o.kt)("p",null,"scalac and scala are the primitive tools of the Scala toolbox.\nWe won't use them directly.\nInstead we will use the sbt build tool which uses scalac and scala underneath."),(0,o.kt)("h2",{id:"sbt"},"sbt"),(0,o.kt)("p",null,"sbt is the oldest and most used build tool of the Scala ecosystem."),(0,o.kt)("p",null,"It helps you configure and organize your Scala project, so that you can compile it, test it, run it, package it and deploy it."),(0,o.kt)("p",null,"The main configuration of an sbt project is written in a ",(0,o.kt)("inlineCode",{parentName:"p"},"build.sbt")," file at the root of a project. "),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-scala",metastring:'title="build.sbt"',title:'"build.sbt"'},'scalaVersion := "3.1.0" // Using the version 3.1.0 of the Scala language\n\n// Using the scalajs-dom library to interact with a web page\nlibraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.2.0" \n')),(0,o.kt)("p",null,"The main Scala files of an sbt project are written in the ",(0,o.kt)("inlineCode",{parentName:"p"},"src/main/scala")," folder and the test files are written in the ",(0,o.kt)("inlineCode",{parentName:"p"},"src/main/test")," folder."),(0,o.kt)("p",null,"We will use ",(0,o.kt)("a",{parentName:"p",href:"#coursier"},"Coursier")," to install sbt."),(0,o.kt)("h2",{id:"vs-code"},"VS Code"),(0,o.kt)("p",null,"Visual Studio Code (VS Code) is a lightweight code editor created by Microsoft.\nIt supports an incredible number of languages through its extension system."),(0,o.kt)("p",null,"Its more popular extension for Scala is called Metals.\nWe will use VS Code and Metals to write and navigate Scala code."),(0,o.kt)("p",null,"We will also use the Live Share extension to allow several people to work on the same code files remotely."),(0,o.kt)("h2",{id:"git-and-github"},"Git and Github"),(0,o.kt)("p",null,"Git is the most popular distributed version control system.\nIt helps developers work on the same project asynchronously and to manage different versions of that project."),(0,o.kt)("p",null,"In the Git terminology, a project is called a repository.\nYou can create a repository locally then push it online to make your code available for other programmers.\nYou can also clone someone else's repository, to have it locally, test it, modify it and make it your own project."),(0,o.kt)("p",null,"Github (",(0,o.kt)("a",{parentName:"p",href:"https://github.com/"},"https://github.com/"),") is a free and popular hosting service for git repositories.\nThat's where you should go to find some cool projects, run them locally and even contribute to them."))}d.isMDXComponent=!0}}]);