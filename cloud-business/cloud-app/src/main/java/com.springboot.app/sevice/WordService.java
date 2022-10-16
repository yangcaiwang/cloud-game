package com.springboot.app.sevice;

import com.springboot.app.domain.entity.word.WordUserStatus;

public interface WordService {

    WordServiceResponse init(WordServiceRequest request);

    class WordServiceRequest {
        private Long userId;

        public WordServiceRequest() {
        }

        public WordServiceRequest(Long userId) {
            this.userId = userId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    class WordServiceResponse {
        private WordUserStatus wordUserStatus;

        public WordServiceResponse() {
        }

        public WordServiceResponse(WordUserStatus wordUserStatus) {
            this.wordUserStatus = wordUserStatus;
        }

        public WordUserStatus getWordUserStatus() {
            return wordUserStatus;
        }

        public void setWordUserStatus(WordUserStatus wordUserStatus) {
            this.wordUserStatus = wordUserStatus;
        }
    }
}
