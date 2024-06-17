#!/bin/bash

npm i -g commitizen && \
npm i -g cz-conventional-changelog && \

echo 'exec < /dev/tty && git cz --hook || true' > .git/hooks/prepare-commit-msg && \
sudo chmod +x .git/hooks/prepare-commit-msg && \

echo '{ "path": "cz-conventional-changelog" }' > ~/.czrc && \

echo ' '
echo 'Commitizen configured'