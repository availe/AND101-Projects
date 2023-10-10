# AND101 Project 4 - CodeMath

Submitted by: **Availe**

Time spent: **2** hours spent in total

## Summary

**CodeMath** is an android app that takes in a bill amount and a tip percentage, and calculates how much your tip and total bill are.

## Application Features

The following REQUIRED features are completed:

- [X] At least one (1) user input
- [X] At least one (1) interactive View
- [X] At least one (1) output of a calculation based on the user input

The following STRETCH features are implemented:

- [X] Implement ViewBinding to reduce boilerplate code and increase efficiency
- [ ] Add at least one (1) additional functionality

## Video Demo

https://user-images.githubusercontent.com/94802525/273778519-a9281401-9303-4af4-b370-234faf206005.webm

## Notes
- Learned about listeners, String.format("%.2f", string), and viewBinding.
- Stumbled into an issue where after deleting billAmount, it would try to convert non-existant string to double, causing app to crash. Fixed it by checking if p0? had a length of 0, and if so not converting its value to double. I originally thought that it was a null value and tried a null check, but p0 is a char (or similar?) and not an int.

## License

Copyright **2023** - **Availe**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
