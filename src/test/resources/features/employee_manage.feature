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

    シナリオ:書籍情報を登録できる
        前提適切なBaseURIが指定されている
        もし書籍情報を登録する
          | title       | author           | publisher | price |
          | Clean Agile | Robert C. Martin | ドワンゴ   | 2640  |
        ならばHTTPステータスコードとして201が返却される
        かつLocationとして登録した書籍情報にアクセスするURLが返却される
        かつ空のBodyが返却される

  シナリオ:必須項目が指定されていない場合、書籍情報登録が失敗する
      前提適切なBaseURIが指定されている
      もし空の書籍情報を登録する
      ならばHTTPステータスコードとして400が返却される
      かつContentTypeとして"application/json"が返却される
      かつ[code、message、details]がオブジェクトで返却される
      かつcodeとして"0002"が返却される
      かつmessageとして"request validation error is occurred."が返却される
      かつdetailsとして詳細なエラー内容を含むリストが返却される

    シナリオ:書籍情報を変更できる
        前提適切なBaseURIが指定されている
        もし書籍情報を変更する
          | title                   | author    | publisher | price |
          | エクストリームプログラミング | Kent Beck | オーム社   | 2420  |
        ならばHTTPステータスコードとして204が返却される
        かつ空のBodyが返却される
        かつ書籍情報が変更されている
          | title                   | author    | publisher | price |
          | エクストリームプログラミング | Kent Beck | オーム社   | 2420  |

      シナリオ:存在しないIDで書籍情報を変更すると、エラーが返却される
        前提適切なBaseURIが指定されている
        もし存在しないIDで書籍情報を変更する
          | title                   | author    | publisher | price |
          | エクストリームプログラミング | Kent Beck | オーム社   | 2420  |
        ならばHTTPステータスコードとして400が返却される
        かつContentTypeとして"application/json"が返却される
        かつ[code、message、details]がオブジェクトで返却される
        かつcodeとして"0003"が返却される
        かつmessageとして"specified book [id = %s] is not found."が返却される
        かつdetailsとして空のリストが返却される

  シナリオ:指定したIDの書籍情報が削除できる
      前提適切なBaseURIが指定されている
      もしIDを指定して書籍情報を削除する
      ならばHTTPステータスコードとして204が返却される
      かつ空のBodyが返却される
      かつ書籍情報が削除されている

    シナリオ:存在しないIDで書籍情報を削除すると、エラーが返却される
      前提適切なBaseURIが指定されている
      もし存在しないIDで書籍情報を削除する
      ならばHTTPステータスコードとして400が返却される
      かつContentTypeとして"application/json"が返却される
      かつ[code、message、details]がオブジェクトで返却される
      かつcodeとして"0003"が返却される
      かつmessageとして"specified book [id = %s] is not found."が返却される
      かつdetailsとして空のリストが返却される
