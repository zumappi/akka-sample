# akka-sample
Akka のサンプル集

# Actor のサンプル集
## シンプルな Actor の実行
```
sbt "runMain com.github.zumappi.akka.sample.actor.simple.BootSimpleActor"
```
基本的な最低限の実装

## 引数がある Actor の実行
```
sbt "runMain com.github.zumappi.akka.sample.actor.args.BootArgsActor"
```
Actor に対して引数を渡している実装

## 戻り値がある Actor の実行
```
sbt "runMain com.github.zumappi.akka.sample.actor.future.BootFutureActor"
```
Actor が戻り値を返して呼び出し元で参照している実装
`sample`を渡している場合は、タイムアウトが発生し、`Failure`として処理される

## ライフサイクルがある Actor の実行
```
sbt "runMain com.github.zumappi.akka.sample.actor.lifecycle.BootLifeCycleActor"
```
Actor のライフサイクルを確認するための実装
- `preStart``preRestart``postRestart``postStop`をそれぞれの呼び出しタイミングを確認できる
- 例外が発生した場合に、再起動処理が実行される
- `preRestart``postRestart`は親の実装を呼び出すようにしている
    - `preRestart`の場合、子 Actor の停止をしないと再起動ができなくなる
    - `postRestart`の場合、内部的に`preStart`の呼び出しを行っている

## 子 Actor がある Actor の実行
```
sbt "runMain com.github.zumappi.akka.sample.actor.child.BootChildActor"
```
親 Actor から子 Actor を呼び出している
- 子 Actor で発生した例外を親 Actor でハンドリングしている
   - 今回の場合、再起動処理をしている
- 子 Actor で発生した例外と、親 Actor で発生した例外が同タイミングで発生すると、
再起動が1回しか処理されず、子 Actor の例外処理としての再起動処理がマージされる為、
あえてスリープして別々に再起動させている
