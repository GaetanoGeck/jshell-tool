# JShell tool

This project's aim is to provide simple facades to actions on data in formats like plain text, CSV and JSON such that tasks that seem trivial can be performed "trivially" in JShell, the Java Shell.

*Notice:* Currently, this project is a prototype only and its focus is that it's "easy to get things done", not on efficiency. For example, some functions currently offer a stream from an input source only after gathering the whole collection -- even though technically, it could be done the other way around.
