# 共同輸送システムプロジェクト 共通フレームワーク

## 概要・目的
このリポジトリは、共同輸送システムプロジェクトの共通フレームワークを管理するものです。フレームワークは、システム全体で再利用可能な機能やコンポーネントを提供し、プロジェクトの効率化とコードの再利用性を高めることを目的としています。

## 前提環境
- Java 17 以上
- Maven
- その他、各プロジェクトに必要な外部ライブラリ

## ビルド・起動手順
1. リポジトリをクローンします。
    ```bash
    git clone https://github.com/NextLogisticsJapan/release_OSS.git
    ```
2. プロジェクトディレクトリに移動します。
    ```bash
    cd release_OSS/co-transport-backend-common
    ```
3. 依存関係をインストールし、ビルドします。
    ```bash
    mvn clean install
    ```

## テスト手順
新機能を追加する場合、必ずユニットテストを作成し、CIでのテストをパスするようにしてください。

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

## 処理概要
- 共通処理：ログ、エラーハンドリング
- データアクセス：データベース操作、DAO、リポジトリ
- Webモジュール：REST API共通機能、エンドポイント処理、レスポンス処理
- セキュリティモジュール：認証機能、認可機能、セキュリティ設定

### 技術スタック
フレームワークを他のプロジェクトに追加するには、`pom.xml`に以下の依存関係を追加してください：
```xml
<dependency>
    <groupId>com.next.logistic</groupId>
    <artifactId>common-adapter-web</artifactId>
    <version>0.0.1</version>
</dependency>
<dependency>
    <groupId>com.next.logistic</groupId>
    <artifactId>common-core</artifactId>
    <version>0.0.1</version>
</dependency>
```

## コーディング規約
- コーディングスタイルは`Google Java Style Guide`に従う
- 開発は`develop`ブランチで行い、安定版は`main`ブランチにマージ

## 処理概要
フレームワークは以下の主要な処理を提供します：
- 共通ビジネスロジック
- データアクセス処理
- Web API共通機能
- セキュリティ機能

## 問合せ・要望
問題が発生した場合や質問がある場合は、[サポートページ](https://github.com/NextLogisticsJapan/framework.git/issues)にてIssueを作成してください。

## ライセンス
このプロジェクトは [MITライセンス](LICENSE.txt) のもとで公開されています。  
詳細についてはリポジトリ内の `LICENSE` ファイルをご確認ください

## 免責事項
このソフトウェアは「現状のまま」提供され、明示または黙示を問わず、いかなる保証も行いません。

## その他
プロジェクトへの貢献を歓迎します。貢献する前に、`CONTRIBUTING.md`を必ず確認してください。
