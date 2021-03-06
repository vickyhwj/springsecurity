package com.erdangjiade.spring.security.tool;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

  public class AntUrlPathMatcher implements UrlMatcher {
      private boolean requiresLowerCaseUrl;
      private final PathMatcher pathMatcher;
      public AntUrlPathMatcher()   {
          this(true);

  }
      public AntUrlPathMatcher(final boolean requiresLowerCaseUrl)
      {
          this.requiresLowerCaseUrl = true;
      this.pathMatcher = new AntPathMatcher();
      this.requiresLowerCaseUrl = requiresLowerCaseUrl;
      }

      @Override
    public Object compile(final String path) {
          if (this.requiresLowerCaseUrl) {
              return path.toLowerCase();
              }
          return path;
      }

      public void setRequiresLowerCaseUrl(final boolean requiresLowerCaseUrl){

          this.requiresLowerCaseUrl = requiresLowerCaseUrl;
      }

      @Override
    public boolean pathMatchesUrl(final Object path, final String url) {
          if (("/**".equals(path)) || ("**".equals(path))) {
              return true;
              }

          return this.pathMatcher.match((String)path, url);
      }

      @Override
    public String getUniversalMatchPattern() {
          return"/**";
      }

      @Override
    public boolean requiresLowerCaseUrl() {
          return this.requiresLowerCaseUrl;
      }

      @Override
    public String toString() {
          return super.getClass().getName() + "[requiresLowerCase='"
      + this.requiresLowerCaseUrl + "']";
      }
  }
