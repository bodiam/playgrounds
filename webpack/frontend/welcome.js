'use strict';

export default function(message) {
    if (NODE_ENV === 'dev') {
        console.log(message);
    }
    alert(`Welcome ${message}`);
}
