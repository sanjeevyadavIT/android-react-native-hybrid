echo "Installing brew packages.."
if ! which brew &> /dev/null; then
  echo "Installing Home Brew.."
  /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
fi

echo "Installing React native packages.."
if ! which yarn &> /dev/null; then
  echo "Installing Yarn.."
  HOMEBREW_NO_AUTO_UPDATE=1 brew install yarn
fi

cd reactNative
#yarn
yarn install --frozen-lockfile  --modules-folder $(pwd)/node_modules

#done for getting higher remote cache hit. Without this script remote cache hits are around 93%
#with AGP 4.x it touches 99%