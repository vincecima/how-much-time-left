# How Much Time Left [![Build Status](https://travis-ci.org/vincecima/how-much-time-left.svg?branch=master)](https://travis-ci.org/vincecima/how-much-time-left)

TODO: Write a project description

## Installation

TODO: Describe the installation process

To produce a single executable jar:
`gradle shadowJar`

To execute the jar:
`java -jar build/libs/how-much-time-left-all.jar`

TODO: Write usage instructions

## Roadmap

- [ ] Serialize request validation errors
  - [x] Throw violations from failed validator
  - [x] Use Exception Mapper to convert violations into response
  - [ ] Extend VTor to include human readable failure message
- [ ] DEBUG logging in development, INFO everywhere else
- [ ] Figure out why JSONResponseTypeFilter works only as a before filter

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credit

## License

The MIT License (MIT)

Copyright (c) 2015 Vince Cima

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.