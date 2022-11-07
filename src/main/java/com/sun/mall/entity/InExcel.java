package com.sun.mall.entity;

import com.sun.mall.utils.ExcelImport;
import lombok.Data;

@Data
public class InExcel {
    @ExcelImport("投票の形式")
    private String voteType;

    @ExcelImport("匿名")
    private String anonymous;

    @ExcelImport("投票作成者だけが結果を見ることができる")
    private String partOfAnonymous;

    @ExcelImport("投票中に投票結果を表示する")
    private String showAmongRes;

    @ExcelImport("誰でもオプションを追加できるようにする")
    private String viewByAll;

    @ExcelImport("投票可能数")
    private String votesCount;

    @ExcelImport("チャット チャネルの種類")
    private String channelType;

    @ExcelImport("実行されたアクション")
    private String operation;

    @ExcelImport("チャットチャンネル数の増減")
    private String channelMembersChange;

    @ExcelImport("投票者数")
    private String voterCount;

    @ExcelImport("投票者が投稿者かどうか")
    private String voterType;

    @ExcelImport("テストできるか")
    private String aim;

    @ExcelImport("NO")
    private String no;

    public String getStep() {
        String step = "1.チャット欄に「/poll」と入力する\n" +
                "2.投票のパラメータを入力する/poll\n" +
                "3.「作成」をクリックする\n";
        if ("選択".equals(voteType)) {
            step += "4.投票を行う\n";
        } else {
            step += "4.質問に答える\n";
        }
        step += "5.投票を削除する";
        step = step.trim();
        return step;
    }

    public String getConditions() {
        String conditions = "投票の形式：" + voteType + "\n"+
//                "匿名：" + anonymous + "\n" +
//                "投票作成者だけが結果を見ることができる：" + partOfAnonymous + "\n" +
//                "投票中に投票結果を表示する：" + showAmongRes + "\n" +
//                "誰でもオプションを追加できるようにする：" + viewByAll + "\n" +
//                "投票可能数：" + votesCount + "\n" +
//                "チャット チャネルの種類：" + channelType + "\n"+
                "実行されたアクション：" + operation + "\n" ;
//                "チャットチャンネル数の増減：" + channelMembersChange + "\n" +
//                "投票者数：" + voterCount + "\n"+
//                "投票者が投稿者かどうか：" + voterType + "\n";

        conditions = conditions.trim();
        return conditions;

    }
}
