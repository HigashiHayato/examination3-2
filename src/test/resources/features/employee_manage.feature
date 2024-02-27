# language: ja

機能: 書籍情報管理

  シナリオ:ルートURLにアクセスできる
    前提適切なBaseURIが指定されている
    もしルートURLにアクセスする
    ならばHTTPステータスコードとして200が返却される

  シナリオ:書籍情報を一覧できる
    前提適切なBaseURIが指定されている
    もしすべての書籍情報を取得する
    ならばHTTPステータスコードとして200が返却される
    かつContentTypeとして"application/json"が返却される
    かつ[ID、タイトル、著者、出版社、価格]がリストで返却される

  シナリオ:指定したIDの書籍情報が取得できる
    前提適切なBaseURIが指定されている
    もしIDを指定して書籍情報を取得する
    ならばHTTPステータスコードとして200が返却される
    かつContentTypeとして"application/json"が返却される
    かつ[ID、タイトル、著者、出版社、価格]がオブジェクトで返却される

  シナリオ:存在しないIDで書籍情報を取得すると、エラーが返却される
    前提適切なBaseURIが指定されている
    もし存在しないIDで書籍情報を取得する
    ならばHTTPステータスコードとして400が返却される
    かつContentTypeとして"application/json"が返却される
    かつ[code、message、details]がオブジェクトで返却される
    かつcodeとして"0003"が返却される
    かつmessageとして"specified book [id = %s] is not found."が返却される
    かつdetailsとして空のリストが返却される
