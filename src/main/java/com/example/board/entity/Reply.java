package com.example.board.entity;

import com.example.board.entity.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int recommendCnt;

    public static Reply createReply(String content, Board board, Member member) {
        Reply reply = new Reply();
        reply.content = content;
        reply.board = board;
        reply.member = member;
        reply.recommendCnt = 0;
        return reply;
    }

    public void addRecommendation(int cnt) {
        recommendCnt += cnt;
    }

    public void removeRecommendation(int cnt) {
        recommendCnt -= cnt;
    }
}
