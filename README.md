# 共同輸送システムプロジェクト 共通フレームワーク

## 概要・目的
このリポジトリは、共同輸送システムプロジェクトの共通フレームワークを管理するものです。フレームワークは、システム全体で再利用可能な機能やコンポーネントを提供し、プロジェクトの効率化とコードの再利用性を高めることを目的としています。

## 前提環境
- Java 17 以上 24 以下
- Maven 3.9 

## ビルド・起動手順
1. リポジトリをクローンします。
    ```bash
    git clone https://github.com/ODS-IS-CAVC/co-transport-backend-common.git
    ```
2. プロジェクトディレクトリに移動します。
    ```bash
    cd co-transport-backend-common
    ```
3. 依存関係をインストールし、ビルドします。
    ```bash
    mvn clean install
    ```

## テスト手順
以下のコマンドで全てのテストを実行できます：
```bash
mvn test
```

## 設計標準
### 主な機能
- 共通ロジックモジュール
  - 業務ロジック
  - ユーティリティクラス
  - 共通処理

- データアクセスモジュール
  - データベース操作
  - DAOクラス
  - リポジトリクラス

- Webモジュール
  - REST API共通機能
  - エンドポイント処理
  - レスポンス処理

- セキュリティモジュール
  - 認証機能
  - 認可機能
  - セキュリティ設定

## コーディング規約
- コーディングスタイルは`Google Java Style Guide`に従う
- 開発は`develop`ブランチで行い、安定版は`main`ブランチにマージ

## 処理概要
- 共通処理：ログ、エラーハンドリング
- データアクセス：データベース操作、DAO、リポジトリ
- Webモジュール：REST API共通機能、エンドポイント処理、レスポンス処理
- セキュリティモジュール：認証機能、認可機能、セキュリティ設定

## 問合せ及び要望に関して
- 本リポジトリは現状は主に配布目的の運用となるため、IssueやPull Requestに関しては受け付けておりません。

## ライセンス
- このプロジェクトは [MITライセンス](LICENSE.txt) のもとで公開されています。  
- 特筆が無い限り、ソースコードおよび関連ドキュメントの著作権はNEXT Logistics Japan株式会社に帰属します。

## 免責事項
- 本リポジトリの内容は予告なく変更・削除する可能性があります。
- 本リポジトリの利用により生じた損失及び損害等について、いかなる責任も負わないものとします。


