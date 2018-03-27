export function post(endpoint, data) {
    return fetch(endpoint, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: getFormBody(data)
    })
}

function getFormBody(obj) {
    return Object.keys(obj).map(key =>
        encodeURIComponent(key) + '=' + encodeURIComponent(obj[key])
    ).join('&');
}