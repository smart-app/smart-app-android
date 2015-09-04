/**
 * Created by sky on 15/8/15.
 */

function Pager(option){

    var opt  = option ||{};
    this.isLoading = false;
    this.pageIndex = opt.pageIndex||1;
    this.pageSize = opt.pageSize||10;
    this.defaultBottomHeight= opt.defaultBottomHeight||40;
    this.pagerBottom = document.getElementById(opt.pagerBottomId||'pagerBottom');
    this.pagerLoadingHtml=opt.pagerLoadingHtml||"正在加载";
    this.pagerLoadedHtml=opt.pagerLoadedHtml||"已全部加载完成";
}


Pager.prototype.init = function(loadData){
    var self  = this;
    var loadPageData = function(){
            console.log('>>>>>>load next page');
            self.isLoading = true;
            loadData(function(isAllLoaded){
                self.isLoading = false;
                if(isAllLoaded){
                    document.getElementById('loading').style.display="none";
                    self.pagerBottom.innerHTML= self.pagerLoadedHtml;
                    setTimeout(function(){
                        self.pagerBottom.style.display='none';
                    },2000)
                    window.removeEventListener('scroll', loadNextPage, false)
                }else{
                    if(self.pageIndex ==1){
                        document.getElementById('loading').style.display="none";
                        self.pagerBottom.style.display='';
                        self.pagerBottom.innerHTML= self.pagerLoadingHtml;
                    }
                    ++self.pageIndex;
                }
            });
    };

    var loadNextPage = function(){
        if(!self.isLoading && self.isScrollBottom()){
            loadPageData();
        }else{
            console.log('当前页正在加载pageIndex:' + self.pageIndex);
        }
    }

    loadPageData();

    window.addEventListener('scroll', loadNextPage, false);
}


Pager.prototype.isScrollBottom = function(){
    var scrollTop = window.pageYOffset
        || document.documentElement.scrollTop
        || document.body.scrollTop
        || 0;
    var clientHeight = document.documentElement ? document.documentElement.clientHeight : document.documentElement.clientHeight;
    var scrollHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);

    console.log(+new Date() + '>>>scrollTop:' + scrollTop + "  clientHeight:" + clientHeight + " scrollHeight:" + scrollHeight);
    return scrollTop + clientHeight + this.defaultBottomHeight > scrollHeight;
}

