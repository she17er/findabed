# form-urlencoded-post
Jquery post alternative for application/x-www-form-urlencoded post requests.

Can't use jquery for size reasons?  Because you can't access the dom?  Trying to post in React Native? We gotchu..

### Installation
```shell
$ npm install --save form-urlencoded-post
```

### Example

```javascript
import {post} from 'form-urlencoded-post';

let user = {name:'joe', skills:['dank memes']};

post('https://www.myapiurl.com/createUser', user).then(res => {
    console.log("response:", res)
})
```
The post() call will return a [thenable](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise/then) from a [fetch()](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) call.

You can also just [add the code](index.js) to your source if you need some customization. At the moment it's only 16 lines.