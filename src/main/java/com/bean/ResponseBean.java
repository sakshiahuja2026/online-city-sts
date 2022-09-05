package com.bean;

import lombok.Data;

@Data
public class ResponseBean<T> {
  T data;
  String msg;
}
