    // 给你下个hello world $ -》 很常用，这个表示网页加载的时候会调用 算了，可能是加载的太快了
    // 1. 首先最重要的是你要知道怎么选择网页里的那些元素， 你看我们通过id来选择，获得元素
    $(document).ready(function(){
        console.log("hello world!");
    });
    var btn = $('#translationButton');
    console.log(btn)