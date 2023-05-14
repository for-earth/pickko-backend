package com.forus.picko.domain;

public enum GrantType {
    authorization_code {
        @Override
        public String toString() {
            return "authorization_code";
        }
    },
    refresh_token {
        @Override
        public String toString() {
            return "refresh_token";
        }
    },
}
