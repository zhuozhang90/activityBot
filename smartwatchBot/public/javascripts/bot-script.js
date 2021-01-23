var botui = new BotUI('help-bot');

var searchOperation;

var historyList = []
var historyResponseList = []
var index = 0;

function init() {
    botui.message.add({
        delay: 500,
        loading: true,
        content: 'Hi! What would you like to do?'
    }).then(function () {
        return botui.action.button({
            action: [
                {
                    text: 'Brute Force (Plain File Search)',
                    value: 'brute'
                },
                {
                    text: 'Lucene',
                    value: 'lucene'
                },
                {
                    text: 'MongoDB',
                    value: 'mongo'
                },
                {
                    text: 'MySql',
                    value: 'mysql'
                },
                {
                    text: 'History',
                    value: 'history'
                }
            ]
        });
    }).then(function (res) {

        var message = " ";
        searchOperation = res.value;
        if (res.value === "brute") {
            message = 'You\'ve chosen Brute Force';
        }
        else if (res.value === "lucene") {
            message = 'You\'ve chosen Lucene Search';
        }
        else if (res.value === "mongo") {
            message = 'You\'ve chosen MongoDB';
        }
        else if (res.value === "mysql") {
            message = 'You\'ve chosen MySQL';
        }
        else if (res.value === "history") {
            for (var i = 0 ; i < historyList.length ; i++){
                //message += (i+1) + '. ' + historyList[i].value + '\n' + '</br>';
                message += (i+1) + '. ' + historyList[i].value + '</br>'+ historyResponseList[i] + '</br>' + '</br></br>';
                console.log(historyList[i].value);
            }
            return botui.message.add({
                type: 'html',
                delay: 1000,
                loading: true,
                content: message
            }).then(init);
        }

        return botui.message.add({
            type: 'html',
            delay: 1000,
            loading: true,
            content: message
        });
    }).then(function () {
        return botui.action.text({
            action: {
                placeholder: 'Please enter your search query'
            }
        });
    }).then(function (queryParam) { // get the result

        queryString(queryParam, searchOperation);
    });
}
var printResponse = function (response) {
    console.log(response);
    historyResponseList[index++] = response
    botui.message.add({
        delay: 500,
        loading: true,
        content: response
    });
}

var queryString = function (queryParam, searchOperation) {
    historyList[index] = queryParam;

    const url = 'http://localhost:8080/searchResult?searchInput='+queryParam.value+'&database='+searchOperation.value;
    const data = {
        searchInput: 'sample'
    };
    const otherParam = {
      headers:{
      },
      method: "GET"
    };
    fetch(url, otherParam)
        .then(response => response.json())
        .then(json => printResponse(json.value))
        .then(function () {
            return botui.action.button({
                action: [
                    {
                        text: 'Search again',
                        value: 'search'
                    },
                    {
                        text: 'Show me all the options',
                        value: 'options'
                    }
                ]
            });
        }).then(function (res) {
            if(res.value === 'options') {
                return botui.message.add({
                    delay: 1000,
                    loading: true,
                    content: 'Sure'
                }).then(init);
            } else {
                return botui.action.text({
                    action: {
                        placeholder: 'Please enter your search query'
                    }
                }).then(function (queryParam) { // get the result
                    queryString(queryParam, searchOperation);
                });
            }
        });
}

init();

