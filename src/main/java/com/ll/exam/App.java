package com.ll.exam;

import java.util.Scanner;

public class App {
    private Scanner sc;

    public App() {
        sc = new Scanner(System.in);
    }

    WiseSayingController wiseSayingController = new WiseSayingController(sc);

    public void run() {
        System.out.println("== 명연 SSG ==");

        outer:
        while(true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch(rq.getPath()) {
                case "등록" :
                    wiseSayingController.write(rq);
                    break;
                case "삭제" :
                    wiseSayingController.remove(rq);
                    break;
                case "수정" :
                    wiseSayingController.modify(rq);
                    break;
                case "목록" :
                    wiseSayingController.list(rq);
                    break;
                case "종료" :
                    System.out.println("SSG 명령이 종료되었습니다.");
                    break outer;
            }
        }

        sc.close();
    }

}
