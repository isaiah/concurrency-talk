require 'net/http'
require 'uri'

DOIS = %w(10.1007/3-540-29623-9 10.1007/978-3-642-38954-2 10.1007/978-3-540-74339-2)

def serial
  count = 0
  DOIS.each do |doi|
    uri = URI("https://search.crossref.org/?q=${doi}")
    resp = Net::HTTP.get_response(uri)
    count += 1 if resp.code == '200'
  end

  puts count
end

def concurrent
  count = 0
  threads = DOIS.map do |doi|
    Thread.new do
      uri = URI("https://search.crossref.org/?q=${doi}")
      resp = Net::HTTP.get_response(uri)
      count += 1 if resp.code == '200'
    end
  end

  threads.each(&:join)
  puts count
end

if __FILE__ == $0
  #serial
  concurrent
end
